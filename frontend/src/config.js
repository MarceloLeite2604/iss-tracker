const dev = {
    'backend-base-server': 'http://localhost:8080'
};

const prod = {
    'backend-base-server': 'http://example.com'
};

const config = process.env.REACT_APP_STAGE === 'production' ? prod : dev;

export default {
    'api-base-path': '/api/v1',
    ...config
};
