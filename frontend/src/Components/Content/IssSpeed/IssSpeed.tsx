import React, { Component } from 'react'
import IssInformationService from "../../../Services/IssInformationService";
import { IssInformation } from '../../../Model';
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

    constructor(props: any) {
        super(props);
        this.issInformationService = new IssInformationService();
        this._issInformation$ = this.issInformationService.information;
        this.state = this.initialState;
    }

    componentDidMount() {
        this._issInformation$.subscribe(issInformation => this.setState({issInformation}));
        this.issInformationService.getInformation();   
    }

    renderAverageSpeed() {
        return (
            <p>
                {`ISS is flying at an astonishing speed of ${this.state.issInformation.averageSpeed.toLocaleString(undefined, {maximumFractionDigits: 2})} km/h.`}
            </p>
        )
    }

    renderAverageSpeedNotAvailable() {
        return (
            <p> 
                We are trying to calculate ISS average speed. Please try again in a few minutes.
            </p>
        )
    }

    render() {
        if (this.state.issInformation === undefined ) {
            return this.renderAverageSpeedNotAvailable();
        }
        return this.renderAverageSpeed();
    }
}