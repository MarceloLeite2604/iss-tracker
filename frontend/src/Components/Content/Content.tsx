import React, { Component, CSSProperties } from "react";

import { Container, Col, Row } from "react-bootstrap";

import IssRouteMap from "./IssRouteMap/IssRouteMap";
import IssSpeed from "./IssSpeed/IssSpeed";

class Content extends Component {

    private readonly rowStyle = {
        marginTop: '1rem' 
    } as CSSProperties;

    render() {
        return (
            <Container>
                <Row style={this.rowStyle}>
                    <Col className='d-flex justify-content-center'>
                        <IssRouteMap />
                    </Col>
                </Row>
                <Row style={this.rowStyle}>
                    <Col className='d-flex justify-content-center'>
                        <IssSpeed />
                    </Col>
                </Row>
            </Container>
        );
    }
}

export default Content