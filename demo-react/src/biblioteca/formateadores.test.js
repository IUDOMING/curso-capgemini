import { titleCase } from "./formateadores";

describe.only("Pruebas de los formateadores", () => {
  describe.only("titleCase", () => {
    [['mud man', 'Mud Man'],['SAMUEL JACKSON','Samuel Jackson'],
    ['interCaLado','Intercalado'],['aÑoranza sÉptima','Añoranza Séptima'],
    ['white','White'],[3487,3487],
    [null, null],]
    .forEach(([origen, resultado]) => {
        it(`${origen} => ${resultado}`, function() {
            expect (titleCase(origen)).toBe(resultado)
        })
    })
  })
})