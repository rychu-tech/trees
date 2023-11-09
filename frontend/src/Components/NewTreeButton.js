// NewTreeButton.js
import React, { useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import axiosInstance from '../axiosConfig';
import '../Styles/Popup.css';

const NewTreeButton = () => {
  const [modal, setModal] = useState(false);
  const [treeName, setTreeName] = useState(null);

  const toggleModal = () => {
    setModal(!modal);
  };

  const addTree = () => {
    if (treeName === null || treeName.trim().length === 0) {
      alert('Field cannot be empty!');
    } else {
      axiosInstance.post('/trees', {
        name: treeName,
      })
        .then(() => {
          alert('Successfully added tree');
          window.location.reload(false);
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
    }
  };

  return (
    <div className="new-tree-button-container">
      <Button
        variant="contained"
        size="large"
        onClick={toggleModal}
        className="btn-modal"
      >
        ADD NEW TREE
      </Button>
      {modal && (
        <div className="modal">
          <div onClick={toggleModal} className="overlay" />
          <div className="modal-content">
            <div>Add New Tree</div>
            <TextField
              label="Tree Name"
              variant="standard"
              size="large"
              onChange={(e) => setTreeName(e.target.value)}
              defaultValue={''}
              autoComplete="off"
            />
            <br />
            <Button
              variant="contained"
              size="large"
              onClick={addTree}
            >
              SAVE
            </Button>
          </div>
        </div>
      )}
    </div>
  );
};

export default NewTreeButton;
