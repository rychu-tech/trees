// DeleteTreePopup.js
import React, { useState } from 'react';
import axiosInstance from '../axiosConfig';
import '../Styles/Popup.css';
import { DeleteOutline } from '@mui/icons-material';
import Button from '@mui/material/Button';

const DeleteTreePopup = ({ treeId }) => {
  const [modal, setModal] = useState(false);

  const toggleModal = () => {
    setModal(!modal);
  };

  const deleteTree = () => {
    window.sessionStorage.removeItem('treeId');
    axiosInstance.delete(`/trees/${treeId}`)
      .then(() => {
        alert('Successfully deleted tree!');
        window.location.href = '/';
      })
      .catch((error) => {
        alert(error.response.data.message);
      });
  };

  return (
    <div className="delete-tree-button-container">
      <DeleteOutline color="action" fontSize="large" onClick={toggleModal} />
      {modal && (
        <div className="modal">
          <div onClick={toggleModal} className="overlay" />
          <div className="modal-content">
            <div>Are you sure you want to delete this tree?</div>
            <br />
            <div className="delete-btn">
              <Button
                variant="contained"
                size="large"
                onClick={deleteTree}
              >
                DELETE
              </Button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default DeleteTreePopup;
