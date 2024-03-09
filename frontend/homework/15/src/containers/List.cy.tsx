import React from 'react'
import { List } from './List'
import { Provider } from 'react-redux'
import { store } from '../redux/Store'

describe('<List />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-react
    cy.mount(    <Provider store={store}>
      <List />

    </Provider>)
    cy.contains('Item 1').should('exist')
  })
})

