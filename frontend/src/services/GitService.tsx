import { GitProperties } from '../model';
import { Subject, Observable } from 'rxjs';
import { AbstractService } from './AbstractService';

export default class GitService extends AbstractService {
    private _gitPropertiesSubject : Subject<GitProperties>;

    constructor() {
        super();
        this._gitPropertiesSubject = new Subject<GitProperties>();
    }

    get gitProperties() : Observable<GitProperties> {
        return this._gitPropertiesSubject.asObservable();
    }

    getGitProperties() : void {
        fetch(`${this.BASE_API}/git`)
            .then(response => this.handleErrors(response))
            .then(response => response.json())
            .catch(error => {
                console.log((error as Error).message);
                this._gitPropertiesSubject.next(undefined);
            });
    }
}
