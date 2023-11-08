import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosConfig';

import DeleteTreePopup from './DeleteTreePopup/DeleteTreePopup';

const Menu = ({ selectedTree, onSelectTree }) => {
    const [menuItems, setMenuItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [currentItem, setCurrentItem] = useState(null);

    const handleTreeSelected = (item) => {
      onSelectTree(item);
      setCurrentItem(item);
    };

    useEffect(() => {
      axiosInstance.get('/trees')
        .then((response) => {
          setMenuItems(response.data);
        })
        .catch((error) => {
          alert('Błąd podczas pobierania danych');
        })
        .finally(() => {
          setLoading(false);
        });
    }, []); 

    if (loading) {
      return <p>Ładowanie...</p>;
    }
    if (menuItems) {
        return (
            <div className='menu-items'>
                {menuItems.map((item, index) => (
                    <div 
                        id={item.id}
                        key={index} 
                        
                        className={currentItem === item ? 'menu-item-selected' : 'menu-item'}
                    >
                        <div className='item-name' onClick={() => handleTreeSelected(item)}>{item.name}</div>
                        
                        <div className="delete-outline-button" onClick={() => handleTreeSelected(null)}>
                          <DeleteTreePopup treeId={item.id}/>
                        </div>

                    </div>
                ))}
            </div>
        );
    }
    return <p>Nie udało się pobrać danych.</p>;

    
};

export default Menu;
