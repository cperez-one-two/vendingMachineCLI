The VendingMachine class has a static variable DISPLAY_VERSION which controls how it displays its inventory.

Choosing '0' (or any invalid choice) results in it displaying the items in a single column.
Choosing '1' results in it displaying the items in a 4-column grid.
Choosing '2' displays the items in a 4-column grid, and rather than labeling each slot it assumes the slots follow the naming convention '[char representing row][char representing column]' and labels the rows/columns.

