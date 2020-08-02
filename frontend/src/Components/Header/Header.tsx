import React, { Component, CSSProperties } from 'react';
import { Navbar, NavbarBrand } from 'react-bootstrap';

interface HeaderProps {
}

export default class Header extends Component<HeaderProps> {

    private readonly navbarStyle = {
        marginBottom: '1rem'
    } as CSSProperties;

    private readonly navbarBrandStyle = {
        fontSize: '2.5rem'
    } as CSSProperties;

    props : HeaderProps

    constructor(props : HeaderProps) {
        super(props);
        this.props = props;
    }

    render() {
        return (
            <Navbar 
                bg='light' 
                style={this.navbarStyle} 
                className='d-flex justify-content-center'>
                <NavbarBrand style={this.navbarBrandStyle}>ISS Tracker</NavbarBrand>
            </Navbar>
        );
    }
}