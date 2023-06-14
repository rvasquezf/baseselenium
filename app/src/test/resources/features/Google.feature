@Google
Feature: Probar la funcionalidad de búsqueda de Google

Scenario: Como usuario ingreso un criterio de búsqueda de Google
 Given que estoy en la página de búsqueda de Google
 When introduzco un criterio de búsqueda
 And haga click en el botón de búsqueda
 Then los resultados coinciden con los criterios

 #* Given = dado
 #* When = Cuando
 #* Y = and
 #* Then = Después