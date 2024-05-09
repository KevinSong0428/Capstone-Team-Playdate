import React, { useState } from 'react';
import "./Modal.css"
const Modal = ({ show, onClose, children }) => {

    if (!show) return null;

    const closeByClickingOutOfModal = (event) => {
        if (event.target === event.currentTarget) {
            onClose();
        }
    };

    return (
        <div className="modal-backdrop" onClick={closeByClickingOutOfModal}>
            <div className="modal-content modal-card" onClick={e => e.stopPropagation()}>
                {children}
                <button onClick={onClose}>X</button>
            </div>
        </div>
    );
};

export default Modal;
