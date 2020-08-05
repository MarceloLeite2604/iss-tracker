import React, { Component } from 'react';
import { Observable } from 'rxjs';
import { Spinner, Image } from 'react-bootstrap';
import { Dimensions } from '../../model';
import './ImageWithPlaceholder.css';

interface ImageWithPlaceholderProps {
    src$: Observable<string>
    dimensions: Dimensions
}

interface ImageWithPlaceholderState {
    src: string
}

export default class ImageWithPlaceholder extends Component<ImageWithPlaceholderProps, ImageWithPlaceholderState> {
    private readonly initialState = {
        src: ''
    } as ImageWithPlaceholderState;

    state : ImageWithPlaceholderState;

    props : ImageWithPlaceholderProps;

    constructor(props: ImageWithPlaceholderProps) {
        super(props);
        this.props = props;
        this.state = this.initialState;
    }

    componentDidMount() : void {
        this.props.src$.subscribe(src => this.setState({ src }));
    }

    renderPlaceholder() : JSX.Element {
        return (
            <div className='ist-iwp-div'>
                <Spinner
                    className='ist-iwp-spinner'
                    animation="border"
                    role="status" />
                <Image
                    src={this.createPlaceholderImage(this.props.dimensions)}
                    fluid />
            </div>
        );
    }

    renderImage() : JSX.Element {
        return (
            <Image
                src={this.state.src}
                fluid />
        );
    }

    createPlaceholderImage(dimensions: Dimensions) : string {
        return 'data:image/svg+xml;charset=UTF-8,' + encodeURIComponent(`
        <svg 
            width="${dimensions.width}" 
            height="${dimensions.height}" 
            xmlns="http://www.w3.org/2000/svg" 
            viewBox="0 0 ${dimensions.width} ${dimensions.height}" 
            preserveAspectRatio="none">
            <g>
                <rect 
                    width="${dimensions.width}" 
                    height="${dimensions.height}"
                    opacity="0.0" />
            </g>
        </svg>
        `);
    }

    render() : JSX.Element {
        if (this.state.src) {
            return this.renderImage();
        }
        return this.renderPlaceholder();
    }
}
