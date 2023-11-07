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
    console.log(selectedTree)
    
  };
  return (
    <div className='page'>
      <div className='menu'>
        <h1>Trees</h1>
        <Menu selectedTree={selectedTree} onSelectTree={handleTreeSelected}/>
        <NewTreeButton />
      </div>
      <div className='tree'>
        {(selectedTree)  ? selectedTree.id : null}
      </div>
    </div>
  );

}

export default App;