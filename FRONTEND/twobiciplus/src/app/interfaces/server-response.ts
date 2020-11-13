export interface ServerResponse {
    result?: any;
    serviceResponse: {
        result: string,
        errorCode?: number,
        errorDetail?: string
    },
    error?: string,
    token?: string,
    userName?: string
}
