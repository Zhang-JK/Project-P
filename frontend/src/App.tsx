import React from 'react';
import logo from './logo.svg';
import './App.css';
import LoginPage from './Pages/Login/index';
import IndexPage from './Pages/Index/index';
import {Route, Routes} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      {/*<header className="App-header">*/}
      {/*  <img src={logo} className="App-logo" alt="logo" />*/}
      {/*  <p>*/}
      {/*    Edit <code>src/App.tsx</code> and save to reload.*/}
      {/*  </p>*/}
      {/*  <a*/}
      {/*    className="App-link"*/}
      {/*    href="https://reactjs.org"*/}
      {/*    target="_blank"*/}
      {/*    rel="noopener noreferrer"*/}
      {/*  >*/}
      {/*    Learn React*/}
      {/*  </a>*/}
      {/*  */}
      {/*</header>*/}
        <Routes>
            <Route path="/login" element={<LoginPage/>} />
            <Route path="/index" element={<IndexPage/>}/>
        </Routes>

    </div>
  );
}

export default App;
