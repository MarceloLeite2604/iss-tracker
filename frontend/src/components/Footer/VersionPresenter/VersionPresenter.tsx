import React, { Component } from 'react';
import GitService from '../../../services/GitService';
import { Observable } from 'rxjs';
import { GitProperties } from '../../../model';
import './VersionPresenter.scss';

type VersionPresenterState = {
    gitProperties : GitProperties
}

export default class VersionPresenter extends Component<unknown, VersionPresenterState> {
    private readonly gitService : GitService;

    private readonly _gitProperties$ : Observable<GitProperties>;

    state : VersionPresenterState;

    constructor() {
        super({});
        this.gitService = new GitService();
        this._gitProperties$ = this.gitService.gitProperties;
        this.state = {} as VersionPresenterState;
    }

    componentDidMount() : void {
        this._gitProperties$.subscribe(gitProperties => {
            console.log('Properties retrieved.');
            this.setState({ gitProperties });
        });
        console.log('Requesting properties.');
        this.gitService.getProperties();
    }

    render() : JSX.Element {
        if (this.state.gitProperties === undefined) {
            return (<></>);
        }
        return (
            <div className='d-flex flex-column justify-content-end version-presenter'>
                <div className='d-flex justify-content-end'>
                    <span>
                        {`commit id: ${this.state.gitProperties.commitIdAbbrev}`}
                    </span>
                </div>
                <div className='d-flex justify-content-end'>
                    <span>
                        {`compiled at: ${this.state.gitProperties.commitTime}`}
                    </span>
                </div>
            </div>
        );
    }
}
