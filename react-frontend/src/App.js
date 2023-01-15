import React, { useEffect, useState } from "react";

import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { LinkContainer } from "react-router-bootstrap";

import Routes from "./Routes";
import { AppContext } from "./lib/contextLib";
import { useNavigate } from "react-router-dom";

import "./App.css";

function App() {
  const nav = useNavigate();
  const [isAuthenticated, userHasAuthenticated] = useState(false);
  const [isAdmin, adminAuth] = useState(false);
  const [isuserType, userType] = useState(false);
  const [isnotPaid, notPaid] = useState(false);

  function handleLogout() {
    userHasAuthenticated(false);
    nav("/movie-ticket-system/");
  }

  useEffect(()=>{
		fetch("http://localhost:8080/api/v1/payment/annualPayment")
  }, []);

  return (
    <div className="App container py-3">
      <Navbar collapseOnSelect bg="light" expand="md" className="mb-3">
        {isAuthenticated ? (
          isAdmin ? (
            <Navbar.Brand className="font-weight-bold text-muted">
              Movie Ticket System
            </Navbar.Brand>
          ) : ( isnotPaid ? (
              <LinkContainer to="/movie-ticket-system/annualfee">
              <Navbar.Brand className="font-weight-bold text-muted">
                Movie Ticket System
              </Navbar.Brand>
            </LinkContainer>
          ) : (
            <LinkContainer to="/movie-ticket-system/theatres">
              <Navbar.Brand className="font-weight-bold text-muted">
                Movie Ticket System
              </Navbar.Brand>
            </LinkContainer>
          )
          )
          ) : (
            <Navbar.Brand className="font-weight-bold text-muted">
              Movie Ticket System
            </Navbar.Brand>
          )}
          
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Nav activeKey={window.location.pathname}>
          {isAuthenticated ? ( 
            isAdmin ? (
              <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
            ) : ( 
              <>
                <LinkContainer to="/movie-ticket-system/account">
                  <Nav.Link>Account</Nav.Link>
                </LinkContainer>
                <Nav.Link onClick={handleLogout}>Logout</Nav.Link>
              </>
            )
          ) : (
            <>
              <LinkContainer to="/movie-ticket-system/signup">
                <Nav.Link>Sign Up</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/movie-ticket-system/">
                <Nav.Link>Login</Nav.Link>
              </LinkContainer>
              <LinkContainer to="/movie-ticket-system/adminLogin">
                <Nav.Link>Admin Login</Nav.Link>
              </LinkContainer>
            </>
          )}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <AppContext.Provider value={{ isAuthenticated, userHasAuthenticated, 
                                    isuserType, userType, isAdmin, adminAuth,
                                    isnotPaid, notPaid}}>
        <Routes />
      </AppContext.Provider>
    </div>
  );
}

export default App;
