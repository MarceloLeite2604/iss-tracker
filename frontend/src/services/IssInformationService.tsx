import { Subject, Observable } from 'rxjs';
import { IssInformation } from '../model';
import { AbstractService } from './AbstractService';

export default class IssInformationService extends AbstractService {
    private _informationSubject : Subject<IssInformation>;

    private _routeMapUrl : Subject<string>;

    constructor() {
        super();
        this._informationSubject = new Subject<IssInformation>();
        this._routeMapUrl = new Subject<string>();
    }

    get information() : Observable<IssInformation> {
        return this._informationSubject.asObservable();
    }

    get routeMapUrl() : Observable<string> {
        return this._routeMapUrl.asObservable();
    }

    getInformation() : void {
        fetch(`${this.BASE_API}/iss-information`)
            .then(response => this.handleErrors(response))
            .then(response => response.json())
            .then((response: IssInformation) => this._informationSubject.next(response))
            .catch(error => {
                console.log((error as Error).message);
                this._informationSubject.next(undefined);
            });
    }

    getRouteMapUrl() : void {
        fetch(`${this.BASE_API}/map`, { mode: 'cors' })
            .then(response => this.handleErrors(response))
            .then(response => response.blob())
            .then(blobImage => this._routeMapUrl.next(URL.createObjectURL(blobImage)))
            .catch(error => {
                console.log((error as Error).message);
                this._routeMapUrl.next(undefined);
            });
    }
}
