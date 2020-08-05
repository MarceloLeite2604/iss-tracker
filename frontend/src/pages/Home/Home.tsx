import React, { Component } from 'react';

import Header from '../../components/Header/Header';
import Content from '../../components/Content/Content';

export default class Home extends Component {
    render() : JSX.Element {
        return (
            <>
                <Header />
                <Content />
            </>
        );
    }
}
