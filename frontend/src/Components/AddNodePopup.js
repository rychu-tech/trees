import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosConfig';
import "../Styles/Popup.css"
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

const AddNodePopup = (props) => {
    const [modal, setModal] = useState(props.modal);
    const [nodeValue, setNodeValue] = useState(null);

    const toggleModal = () => {
        setModal(!modal);
    }

    if(modal) {
        document.body.classList.add('active-modal')
    } 
    else {
        document.body.classList.remove('active-modal')
    }

    const addNode = () => {
        if (!isNaN(nodeValue) && nodeValue !== null) {
            axiosInstance.post('/trees/' + props.treeId + '/nodes', {
                "value": parseInt(nodeValue, 10),
                "parentNodeId": props.parentNodeId
            })
            .then(() => {
              alert("Node added succesfully!")
              window.location.reload(false);
            })
            .catch((error) => {
                alert(error.response.data.message)
            })
        }
        else {
            alert("Node value must be an integer!")
        }



    }

    return <>
    {modal && (
        <div className="modal">
            <div onClick={toggleModal} className="overlay">

            </div>
            <div className="modal-content">
                <div>New Node</div>
                <br></br>
                <div className='number-input'>
                <TextField 
                    label="Node Value" 
                    variant="standard" 
                    size='large'
                    onChange={e => setNodeValue(e.target.value)}
                    defaultValue={""}
                />
                </div>

                <div className='delete-btn'>
                    <Button 
                        variant="contained" 
                        size="large"
                        onClick={() => addNode()}
                    >
                        ADD NODE
                    </Button>
                </div>
                
            </div>
        </div>
    )}
    </>
    
    


};

export default AddNodePopup;