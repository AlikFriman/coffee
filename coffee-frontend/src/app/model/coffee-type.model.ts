/**
 * Сорт кофе.
 */
export interface CoffeeType {

  /**
   * Уникальный идентификатор.
   */
  id: number;

  /**
   * Наименование на русском.
   */
  nameRu: string;

  /**
   * Наименование на английском.
   */
  nameEng: string;

  /**
   * Цена.
   */
  price: number;

  /**
   * Признак наличия сорта кофе.
   */
  available: boolean;
}
