import './App.css';
import {Route, Routes} from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import FeedbackCommentPage from "./Pages/FeedbackCommentPage";
import HomePage from "./Pages/HomePage";
import FeedbackPage from "./Pages/FeedbackPage";
import {TestPage} from "./Pages/TestPage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path="/home" element={<HomePage/>}/>
                <Route path="/login" element={<LoginPage/>}/>
                {/*<Route path="/index" element={<IndexPage/>}/>*/}
                <Route path="/test" element={<TestPage/>}/>
                <Route path="/feedback/*" element={<FeedbackPage/>}/>
            </Routes>
        </div>
    );
}

export default App;
