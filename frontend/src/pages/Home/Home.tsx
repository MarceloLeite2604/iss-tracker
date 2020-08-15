import React, { Component } from 'react';

import Header from '../../components/Header/Header';
import Content from '../../components/Content/Content';
import Footer from '../../components/Footer/Footer';

export default class Home extends Component {
    render() : JSX.Element {
        return (
            <>
                <Header />
                <Content />
                <Footer />
            </>
        );
    }
}
