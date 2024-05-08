import React, { useState } from 'react';
import "./Modal.css"
const Modal = ({ show, onClose, children }) => {
    if (!show) return null;
    const handleBackdropClick = (event) => {

        if (event.target === event.currentTarget) {
            onClose();
        }
    };

    return (
        <div className="modal-backdrop" onClick={handleBackdropClick}>
            <div className="modal-content" onClick={e => e.stopPropagation()}>
                {children}
                <button onClick={onClose}>Close</button>
            </div>
        </div>
    );
};

export default Modal;
