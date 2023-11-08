import './App.css';
import React, { useState, useEffect } from 'react';
import NewTreeButton from './Components/NewTreeButton/NewTreeButton';
import Menu from './Components/Menu';
import axiosInstance from './axiosConfig';

function App() {
  const [selectedTree, setSelectedTree] = useState(null);
  const handleTreeSelected = (item) => {
    axiosInstance.get('/trees/' + item.id)
        .then((response) => {
          setSelectedTree(response.data);
        })
        .catch((error) => {
          alert('Błąd podczas pobierania danych');
        })
        .finally(() => {
          
        });
    
  };
  return (
    <div className='page'>
      <div className='menu'>
        <div className='menu-header-container'>
          <h1>Trees</h1>
        </div>
        <Menu selectedTree={selectedTree} onSelectTree={handleTreeSelected}/>
        <NewTreeButton />
      </div>
      <div className='tree-container'>
        <div className='tree'>
          {(selectedTree)  ? treeRendering([selectedTree]) : <div className='no-tree-selected'>NO TREE SELECTED</div>}
        </div>
      </div>
      
    </div>
  );

}

const treeRendering = (selectedTree) => {
  return (
    <>
      <ul>
        {selectedTree.map((item) => (
          <li key={item.id} className={"node"}>
            <div className="node-content">
              {
                (item.parentNodeId === null) ? "" : <div className="leaf">{item.leafSum}</div>
              }
              <div className="value">
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






export default App;