import React, { Component } from 'react';
import { Navbar } from 'react-bootstrap';
import './Header.scss';

export default class Header extends Component {
    render() : JSX.Element {
        return (
            <Navbar
                className='d-flex justify-content-center ist-navbar'>
                <span className='ist-navbar-brand'>ISS Tracker</span>
            </Navbar>
        );
    }
}
