import React, { Component } from 'react';

import { Container, Col, Row } from 'react-bootstrap';

import IssRouteMap from './IssRouteMap/IssRouteMap';
import IssSpeed from './IssSpeed/IssSpeed';
import './Content.scss';

class Content extends Component {
    render() : JSX.Element {
        return (
            <Container>
                <Row className='ist-row'>
                    <Col className='d-flex justify-content-center'>
                        <IssRouteMap />
                    </Col>
                </Row>
                <Row className='ist-row'>
                    <Col className='d-flex justify-content-center'>
                        <IssSpeed />
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default Content;
