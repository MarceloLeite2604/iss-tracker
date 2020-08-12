import config from '../config';

export abstract class AbstractService {
    readonly BASE_API = config['backend-base-server'] + config['api-base-path'];

    handleErrors(response: Response) : Response {
        if (!response.ok) {
            throw Error(response.statusText);
        }
        return response;
    }
}