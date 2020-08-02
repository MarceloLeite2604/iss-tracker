import React, { Component } from 'react';
import { Observable, Subject } from 'rxjs';

import IssInformationService from "../../../Services/IssInformationService";
import ImageWithPlaceholder from '../../ImageWithPlaceholder/ImageWithPlaceholder';
import { Dimensions } from '../../../Model';
import { Card } from 'react-bootstrap';

const imageDimensions = {
    width: 640,
    height: 460,
} as Dimensions;

export default class RouteMapImage extends Component {

    private readonly issInformationService = new IssInformationService();

    private readonly ISS_INFORMATION_UNAVAILABLE_IMAGE_PATH = 'iss-info-unavailable.png';

    private readonly _routeMapUrl$ : Observable<string>;

    private _redirectRouteMapUrl$ : Subject<string>;

    constructor(props: any) {
        super(props);

        this._routeMapUrl$ = this.issInformationService.routeMapUrl;
        this._redirectRouteMapUrl$ = new Subject<string>();
    }

    componentDidMount() {
        this._routeMapUrl$.subscribe( routeMapUrl => {
            this.handleRouteMapUrlUpdate(routeMapUrl)
        });
        this.issInformationService.getRouteMapUrl();
    }

    handleRouteMapUrlUpdate(receivedRouteMapUrl : string) {
        let routeMapUrl = this.ISS_INFORMATION_UNAVAILABLE_IMAGE_PATH;
        if (receivedRouteMapUrl !== undefined) {
            routeMapUrl = receivedRouteMapUrl;
        }

        this._redirectRouteMapUrl$.next(routeMapUrl);
    }

    render() {
        return (
            <Card>
                <ImageWithPlaceholder src$={this._redirectRouteMapUrl$.asObservable()} dimensions={imageDimensions}/>
            </Card>
        );
    }
}