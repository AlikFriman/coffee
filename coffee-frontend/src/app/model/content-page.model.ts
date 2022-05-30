/**
 * Данные о странице контента.
 */
export interface ContentPage<T> {

  /**
   * Контент.
   */
  content: T[];

  /**
   * Номер страницы.
   */
  number: number,

  /**
   * Размер страницы.
   */
  size: number,

  /**
   * Общее количество страниц.
   */
  totalPages: number,

  /**
   * Количество элементов на странице.
   */
  numberOfElements: number,

  /**
   * Общее количество элементов.
   */
  totalElements: number,

  /**
   * Признак пустой страницы.
   */
  empty: boolean

  /**
   * Признак первой страницы.
   */
  first: boolean,

  /**
   * Признак последней страницы.
   */
  last: boolean,
}
