import { Subject, Observable } from 'rxjs';
import { IssInformation } from '../model';
import config from '../config';

export default class IssInformationService {
    readonly BASE_API = config['backend-base-server'] + config['api-base-path'];

    private _informationSubject : Subject<IssInformation>;

    private _routeMapUrl : Subject<string>;

    constructor() {
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
            .then(response => {
                return this.handleErrors(response);
            })
            .then(response => response.json())
            .then((response: IssInformation) => this._informationSubject.next(response))
            .catch(error => {
                console.log((error as Error).message);
                this._informationSubject.next(undefined);
            });
    }

    handleErrors(response: Response) : Response {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        return response;
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
