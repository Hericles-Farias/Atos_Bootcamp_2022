// A few notes of things that might not be immediately apparent from reading the CSS spec:

// placing a grid child element at a grid-column-start position (Helium, for example), automatically creates that number of columns: 18, in this case

// subsequent child elements "flow" from that new point automatically

// I've set the height of the elements using vw units, but it could be more accurately determined using calc