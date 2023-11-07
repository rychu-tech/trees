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
        <h1>Trees</h1>
        <Menu selectedTree={selectedTree} onSelectTree={handleTreeSelected}/>
        <NewTreeButton />
      </div>
      <div className='tree'>
        {(selectedTree)  ? treeRendering([selectedTree]) : null}
      </div>
    </div>
  );

}

const treeRendering = (selectedTree) => {
  return (
    <>
      <ul>
        {
          selectedTree.map((item) => 
            <li key={item.id} className={"node-"+item.id}>
              
              <div key={item.id}>
                {item.value ? item.value : "ROOT"}
              </div>
              {
                " Cost: " + item.leafSum
              }
              {
                item.children && item.children.length ? treeRendering(item.children) : ''
              }
              
            </li>
          )
        }

      </ul>
          
    </>
  )
}

export default App;