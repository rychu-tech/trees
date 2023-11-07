import React, { useState, useEffect } from 'react';
import axiosInstance from './axiosConfig';

const Menu = () => {
    const [menuItems, setMenuItems] = useState([]);
    const [loading, setLoading] = useState(true);

    const [selectedItem, setSelectedItem] = useState(null);

    const handleItemClick = (item) => {
        setSelectedItem(item);
    };

    useEffect(() => {
      axiosInstance.get('/trees')
        .then((response) => {
          setMenuItems(response.data);
        })
        .catch((error) => {
          console.error('Błąd podczas pobierania danych', error);
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
                        key={index} 
                        onClick={() => handleItemClick(item)}
                        className={selectedItem === item ? 'menu-item-selected' : 'menu-item'}
                    >
                        {item.name}
                    </div>
                ))}
            </div>
        );
    }
    return <p>Nie udało się pobrać danych.</p>;

    
};

export default Menu;
