// Menu.js
import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosConfig';
import DeleteTreePopup from './DeleteTreePopup';

const Menu = ({ selectedTree, onSelectTree }) => {
  const [menuItems, setMenuItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentItem, setCurrentItem] = useState(window.sessionStorage.getItem('treeId'));

  const handleTreeSelected = (item) => {
    onSelectTree(item);
    if (item) {
      window.sessionStorage.setItem('treeId', item.id);
      setCurrentItem(item.id);
    }
  };

  useEffect(() => {
    axiosInstance.get('/trees')
      .then((response) => {
        setMenuItems(response.data);
      })
      .catch(() => {
        alert('Error while fetching data');
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Loading...</p>;
  }

  if (menuItems) {
    return (
      <div className="menu-items">
        {menuItems.map((item, index) => (
          <div
            id={item.id}
            key={index}
            className={currentItem == item.id ? 'menu-item-selected' : 'menu-item'}
          >
            <div className="item-name" onClick={() => handleTreeSelected(item)}>{item.name}</div>
            <div className="delete-outline-button" onClick={() => handleTreeSelected(null)}>
              <DeleteTreePopup treeId={item.id} />
            </div>
          </div>
        ))}
      </div>
    );
  }

  return <p>Failed to fetch data.</p>;
};

export default Menu;
