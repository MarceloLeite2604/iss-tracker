import React, { Component } from 'react';
import { Navbar, NavbarBrand } from 'react-bootstrap';
import './Header.css';

export default class Header extends Component {
    render() : JSX.Element {
        return (
            <Navbar
                bg='light'
                className='d-flex justify-content-center ist-navbar'>
                <NavbarBrand className='ist-navbar-brand'>ISS Tracker</NavbarBrand>
            </Navbar>
        );
    }
}
