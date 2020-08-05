import React, { Component } from 'react';
import IssInformationService from '../../../services/IssInformationService';
import { IssInformation } from '../../../model';
import { Observable } from 'rxjs';

interface IssSpeedState {
    issInformation: IssInformation
}

export default class IssSpeed extends Component {
    readonly initialState : IssSpeedState = {
        issInformation: {
            averageSpeed: 0,
            positions: []

        }
    } as IssSpeedState;

    readonly issInformationService : IssInformationService;

    private readonly _issInformation$ : Observable<IssInformation>;

    state : IssSpeedState;

    constructor() {
        super({});
        this.issInformationService = new IssInformationService();
        this._issInformation$ = this.issInformationService.information;
        this.state = this.initialState;
    }

    componentDidMount() : void {
        this._issInformation$.subscribe(issInformation => this.setState({ issInformation }));
        this.issInformationService.getInformation();
    }

    formatAverageSpeed(averageSpeed : number) : string {
        return averageSpeed.toLocaleString(undefined, { maximumFractionDigits: 2 });
    }

    renderAverageSpeed() : JSX.Element {
        return (
            <p>
                {`ISS is flying at an astonishing speed of 
                ${this.formatAverageSpeed(this.state.issInformation.averageSpeed)} km/h.`}
            </p>
        );
    }

    renderAverageSpeedNotAvailable() : JSX.Element {
        return (
            <p>We are trying to calculate ISS average speed. Please try again in a few minutes.</p>
        );
    }

    render() : JSX.Element {
        if (this.state.issInformation === undefined) {
            return this.renderAverageSpeedNotAvailable();
        }
        return this.renderAverageSpeed();
    }
}
