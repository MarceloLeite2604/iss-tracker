import { Subject } from 'rxjs';
import { IssInformation } from '../Model'

export default class IssInformationService {

    readonly BASE_API = 'http://localhost:8080/api/v1';

    private _informationSubject : Subject<IssInformation>;

    private _routeMapUrl : Subject<string>;

    constructor() {
        this._informationSubject = new Subject<IssInformation>();
        this._routeMapUrl = new Subject<string>();
    }

    get information() {
        return this._informationSubject.asObservable();
    }

    get routeMapUrl() {
        return this._routeMapUrl.asObservable();
    }
    sleep(ms : number) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
      

    getInformation() {
        fetch(`${this.BASE_API}/iss-information`)
            .then(response => {
                return this.handleErrors(response);
            })
            .then(response => response.json())
            .then((response: IssInformation) => this._informationSubject.next(response))
            .catch( error => {
                console.log((error as Error).message);
                this._informationSubject.next(undefined);
            });
    }

    handleErrors(response: Response) {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        return response;
    }

    getRouteMapUrl() {
         fetch(`${this.BASE_API}/map`, {mode: 'cors'})
            .then(response => this.handleErrors(response))
            .then(response => response.blob())
            .then(blobImage => this._routeMapUrl.next(URL.createObjectURL(blobImage)))
            .catch( error => {
                console.log((error as Error).message);
                this._routeMapUrl.next(undefined);
            });
    }
}