import './App.css';
import React, { useState, useEffect } from 'react';
import NewTreeButton from './Components/NewTreeButton';
import Menu from './Components/Menu';
import axiosInstance from './axiosConfig';
import NodePopup from './Components/NodePopup';

function App() {
  const [selectedTree, setSelectedTree] = useState(null);
  const [treeId, setTreeId] = useState(null);
  const [nodeId, setNodeId] = useState(null);
  const [parentNodeId, setParentNodeId] = useState(null);
  const [showNodePopup, setShowNodePopup] = useState(false);
  const [currentItem, setCurrentItem] = useState(null);

  const toggleNodePopup = () => {
    setShowNodePopup(!showNodePopup);
    
  };


  const handleTreeSelected = (item) => {
    let itemId = null;
    if (item) {
      itemId = item.id;
    }
    else if(window.sessionStorage.getItem('treeId')) {
      itemId = window.sessionStorage.getItem('treeId');
    }


    if (itemId) {
      setTreeId(itemId);
      axiosInstance.get('/trees/' + itemId)
      .then((response) => {
        setSelectedTree(response.data);
      })
      .catch((error) => {
        alert('Error while fatching data');
      });
    }
  };
  
  useEffect(() => {
    handleTreeSelected(null);
  }, [])

  const addNode = (item) => {
    toggleNodePopup()
    setCurrentItem(item)
    setNodeId(item.id)
    setParentNodeId(item.parentNodeId)
  }

  const treeRendering = (selectedTree) => {
    return (
      <>
        <ul>
          {selectedTree.map((item) => (
            <li key={item.id} className={"node"}>
              <div className="node-content">
                {(item.parentNodeId === null) ? "" : <div className="leaf">{item.leafSum}</div>}
                <div className={currentItem === item ? 'value-selected' : 'value'} onClick={() => addNode(item)}>
                  {item.value ? item.value : "ROOT"}
                </div>
              </div>
              {item.children && item.children.length ? treeRendering(item.children) : ''}
            </li>
          ))}
        </ul>
      </>
    );
  };

  return (
    <div className='page'>
      <div className='menu'>
        <div className='menu-header-container'>
          <h1>Trees</h1>
        </div>
        <Menu selectedTree={selectedTree} onSelectTree={handleTreeSelected} onToggleModal={toggleNodePopup}/>
        <NewTreeButton />
      </div>
      <div className='tree-container'>
        <div className='tree'>
          {(selectedTree)  ? treeRendering([selectedTree]) : <div className='no-tree-selected'>NO TREE SELECTED</div>}
        </div>
        {showNodePopup && (
          <NodePopup treeId={treeId} modal={showNodePopup} nodeId={nodeId} parentNodeId={parentNodeId}/>
        )}
      </div>
    </div>
  );
}

export default App;
