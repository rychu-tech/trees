import Button from '@mui/material/Button';
import CloseIcon from '@mui/icons-material/Close';
import TextField from '@mui/material/TextField';
import React, { useState, useEffect } from 'react';
import axiosInstance from '../../axiosConfig';
import "./NewTreeButton.css"

const NewTreeButton = () => {
    const [modal, setModal] = useState(false);
    const [treeName, setTreeName] = useState(null);
    const toggleModal = () => {
        setModal(!modal);
    }

    const addTree = () => {
        if (treeName === null || treeName.trim().length === 0) {
            alert("Pole nie może być puste!")
        } 
        else {
            axiosInstance.post('/trees', {
                name: treeName
            })
            .then((response) => {
                alert("Pomyślnie dodano drzewo!");
                window.location.reload(false);
            })
            .catch((error) => {
                alert(error.response.data.message)
            });
        }

        
    }

    if(modal) {
        document.body.classList.add('active-modal')
    } 
    else {
        document.body.classList.remove('active-modal')
    }


    return <div className='new-tree-button-container'>
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
        <div onClick={toggleModal} className="overlay"></div>
        <div className="modal-content">
            <h2>Add New Tree</h2>
            <TextField 
                label="Tree Name" 
                variant="standard" 
                size='large'
                onChange={e => setTreeName(e.target.value)}
                defaultValue={""}
            />
            <br></br>
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
    
    


};

export default NewTreeButton;
