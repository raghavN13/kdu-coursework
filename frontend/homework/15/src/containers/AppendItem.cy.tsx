import React from 'react';
import { AppendItem } from './AppendItem';
import { Provider } from 'react-redux';
import { store } from '../redux/Store';

let item = 1;

describe('<AppendItem />', () => {

  it('renders', () => {
    cy.mount(
      <Provider store={store}>
        <AppendItem />
      </Provider>
    );
  });

  it('adds item on submit', () => {
    const itemName = 'Test Item';

    cy.mount(
      <Provider store={store}>
        <AppendItem />
      </Provider>
    );

    // Type into the input field
    cy.get('.enter-text').type(itemName);

    // Click the submit button
    cy.get('.item-btn').click();

    // Assert that the item is added and displayed
    cy.contains('.actual-list-item p', itemName).should('exist');
  });

  it('deletes item', () => {
    const itemName = 'new Item';

    cy.mount(
        <Provider store={store}>
            <AppendItem />
        </Provider>
    );

    // Type into the input field
    cy.get('.enter-text').type(itemName);

    // Click the submit button
    cy.get('.item-btn').click();

    // Click the delete button of the item using class
    cy.contains('.actual-list-item p', itemName)
        .parent('.actual-list-item')
        .find('.del-btn')
        .click();

    // Assert that the item is removed
    cy.contains('.actual-list-item p', itemName).should('not.exist');


    item++;
});



});
