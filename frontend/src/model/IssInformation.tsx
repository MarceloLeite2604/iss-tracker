
interface Coordinates {
    latitude: number,
    longitude: number
}

interface IssPosition {
    instant: Date,
    coordinates: Coordinates
}

export interface IssInformation {
    averageSpeed: number,
    positions: IssPosition[]
}
