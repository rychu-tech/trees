import React, { useState, useEffect } from 'react';
import axiosInstance from '../axiosConfig';
import "../Styles/Popup.css"
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

const NodePopup = (props) => {
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
                "parentNodeId": props.nodeId
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

    const deleteNode = () => {
        axiosInstance.delete('/trees/' + props.treeId + '/nodes/' + props.nodeId)
        .then(() => {
          alert("Node deleted succesfully!")
          window.location.reload(false);
        })
        .catch((error) => {
            alert(error.response.data.message)
        })
    }
    
    const editNode = () => {
        if (props.parentNodeId === null) {
            alert("Cannot edit root node!");
        }
        else {
            if (!isNaN(nodeValue) && nodeValue !== null) {
                axiosInstance.put('/trees/' + props.treeId + '/nodes/' + props.nodeId, {
                    "value": nodeValue
                })
                .then(() => {
                    alert("Succesfully changed node value!")
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
    }

    return <>
    {modal && (
        <div className="modal">
            <div onClick={toggleModal} className="overlay">

            </div>
            <div className="modal-content">
                <div>Node Editor</div>
                <br></br>
                <div className='number-input'>
                <TextField 
                    label="Node Value" 
                    variant="standard" 
                    size='large'
                    onChange={e => setNodeValue(e.target.value)}
                    defaultValue={""}
                    autoComplete='off'
                />
                </div>

                <div className='buttons'>
                    <div className='add-node-btn'>
                        <Button 
                            variant="contained" 
                            size="large"
                            onClick={() => addNode()}
                            sx={{
                                width: 100,
                            }}
                        >
                            ADD
                        </Button>
                    </div>
                    <div className='delete-btn'>
                        <Button 
                            variant="contained" 
                            size="large"
                            color="error"
                            onClick={() => deleteNode()}
                            sx={{
                                width: 100,
                            }}
                        >
                            DELETE
                        </Button>
                    </div>
                    <div className='edit-btn'>
                        <Button 
                            variant="contained" 
                            size="large"
                            color="success"
                            onClick={() => editNode()}
                            sx={{
                                width: 100,
                            }}
                        >
                            EDIT
                        </Button>
                    </div>
                </div>
                
            </div>
        </div>
    )}
    </>
    
    


};

export default NodePopup;