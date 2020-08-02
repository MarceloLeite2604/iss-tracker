import React, { Component, CSSProperties } from "react";
import { Observable } from "rxjs";
import { Spinner, Image } from "react-bootstrap";
import { Dimensions } from '../../Model';

interface ImageWithPlaceholderProps {
    src$: Observable<string>
    dimensions: Dimensions
}

interface ImageWithPlaceholderState {
    src: string
}

export default class ImageWithPlaceholder extends Component<ImageWithPlaceholderProps, ImageWithPlaceholderState> {

    private readonly divStyle = {
        display: 'inline-block',
        position: 'relative'
    } as CSSProperties;

    private readonly spinnerSyle = {
        position: 'absolute',
        zIndex: 1,
        top: 'calc(50% - 1rem)',
        left: 'calc(50% - 1rem)'
    } as CSSProperties;

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

    componentDidMount() {
        this.props.src$.subscribe(src => this.setState({src}));
    }

    renderPlaceholder() {
        return (
            <div style={this.divStyle}>
                <Spinner 
                    style={this.spinnerSyle}
                    animation="border" 
                    role="status" />
                <Image 
                    src={this.createPlaceholderImage(this.props.dimensions)} 
                    fluid />
            </div>
        );
    }

    renderImage() {
        return (
            <Image
                src={this.state.src} 
                fluid />
        );
    }

    createPlaceholderImage(dimensions: Dimensions) {
        return 'data:image/svg+xml;charset=UTF-8,'+ encodeURIComponent(`
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
    
    render() {
        if (this.state.src) {
            return this.renderImage();
        }
        return this.renderPlaceholder();
    }
}