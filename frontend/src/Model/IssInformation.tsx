
interface Coordinates {
    latitude: Number,
    longitude: Number
}

interface IssPosition {
    instant: Date,
    coordinates: Coordinates
}

export interface IssInformation {
    averageSpeed: Number,
    positions: IssPosition[]
}