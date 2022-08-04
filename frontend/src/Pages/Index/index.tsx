import React, {FC, ReactElement} from "react";


interface AppProps {
}

const IndexPage: FC<AppProps> = ({}: {}): ReactElement => {
    return (
        <div>
            <h1>Index Page!!!</h1>
        </div>
    );
}

export default IndexPage;
