package com.trees.trees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trees.trees.features.tree_structure.controllers.TreeController;
import com.trees.trees.features.tree_structure.helpers.TreeValidator;
import com.trees.trees.features.tree_structure.models.Node;
import com.trees.trees.features.tree_structure.models.NodeView;
import com.trees.trees.features.tree_structure.models.Tree;
import com.trees.trees.features.tree_structure.requests.NodeRequest;
import com.trees.trees.features.tree_structure.requests.TreeRequest;
import com.trees.trees.features.tree_structure.services.NodeService;
import com.trees.trees.features.tree_structure.services.TreeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TreeControllerTest {

	@Mock
	private TreeService treeService;

	@Mock
	private NodeService nodeService;

	@Mock
	private TreeValidator treeValidator;

	@InjectMocks
	private TreeController treeController;

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(treeController).build();
		this.objectMapper = new ObjectMapper();
	}

	@Test
	public void testAddTree() throws Exception {
		when(treeService.addTree(any(TreeRequest.class))).thenReturn(new Tree("SampleTree"));

		ResultActions result = mockMvc.perform(post("/trees")
				.content(objectMapper.writeValueAsString(new TreeRequest("SampleTree")))
				.contentType(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("SampleTree"));
	}

	@Test
	public void testGetTreeList() throws Exception {
		List<Tree> treeList = Arrays.asList(new Tree("Tree1"), new Tree("Tree2"));
		when(treeService.getTreeList()).thenReturn(treeList);

		ResultActions result = mockMvc.perform(get("/trees"));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Tree1"))
				.andExpect(jsonPath("$[1].name").value("Tree2"));
	}

	@Test
	public void testDeleteTree() throws Exception {
		doNothing().when(treeValidator).validateTreeId(1L);

		ResultActions result = mockMvc.perform(delete("/trees/1"));

		verify(treeService).deleteTree(1L);
		result.andExpect(status().isOk());
	}

	@Test
	public void testEditTreeNode() throws Exception {
		doNothing().when(treeValidator).validateTreeId(1L);
		doNothing().when(treeValidator).validateNodeId(2L);
		doNothing().when(treeValidator).validateValue(10);

		when(nodeService.editNode(eq(2L), any(NodeRequest.class), eq(1L))).thenReturn(createMockNode());

		mockMvc.perform(put("/trees/1/nodes/2")
						.content("{\"value\":\"10\"}")
						.contentType("application/json"))
				.andExpect(status().isOk());

		verify(nodeService).editNode(eq(2L), any(NodeRequest.class), eq(1L));
	}

	private Node createMockNode() {
		Node node = new Node();
		node.setId(2L);
		node.setValue(10);
		return node;
	}

	@Test
	public void testDeleteTreeNode() throws Exception {
		doNothing().when(treeValidator).validateTreeId(1L);
		doNothing().when(treeValidator).validateTreeId(2L);
		ResultActions result = mockMvc.perform(delete("/trees/1/nodes/2"));
		result.andExpect(status().isOk());
		verify(nodeService).deleteNode(2L);
	}

	@Test
	public void testGetTree() throws Exception {
		doNothing().when(treeValidator).validateTreeId(1L);

		NodeView mockNodeView = createMockNodeView();
		when(treeService.generateTreeView(1L)).thenReturn(mockNodeView);

		ResultActions result = mockMvc.perform(get("/trees/1"));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.value").value(11))
				.andExpect(jsonPath("$.leafSum").value(10))
				.andExpect(jsonPath("$.children[0].value").value(10))
				.andExpect(jsonPath("$.children[0].leafSum").value(5));

		verify(treeService).generateTreeView(1L);
	}

	private NodeView createMockNodeView() {
		NodeView root = new NodeView(11);
		root.setId(1L);
		root.setLeafSum(10);

		NodeView child1 = new NodeView(10);
		child1.setId(2L);
		child1.setParentNodeId(1L);
		child1.setLeafSum(5);

		root.addChild(child1);

		return root;
	}
}