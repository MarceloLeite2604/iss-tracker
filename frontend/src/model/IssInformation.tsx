
type Coordinates = {
    latitude: number,
    longitude: number
}

type IssPosition = {
    instant: Date,
    coordinates: Coordinates
}

export type IssInformation = {
    averageSpeed: number,
    positions: IssPosition[]
}
