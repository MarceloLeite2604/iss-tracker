import React, { Component } from 'react';
import './Footer.scss';
import VersionPresenter from './VersionPresenter/VersionPresenter';

export default class Footer extends Component {
    render() : JSX.Element {
        return (
            <footer className='d-flex justify-content-end footer '>
                <VersionPresenter />
            </footer>
        );
    }
}
