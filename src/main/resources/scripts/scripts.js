// с сайта https://www.mobafire.com/edit/guide получить список героев (выполнить в консоли разработчика, когда на странице есть выбор героев)
$('#content > div > div.mf-redesign.new-guide > div.col-left > div.new-guide__start > div:nth-child(4) > div.champ-box>div')
    .map(
        (i, d) =>
            ({
                id: d.attributes.getNamedItem('champ-id').value,
                img: d.querySelector('img').src,
                name: d.querySelector('img').title
            })
    ).toArray()
    .forEach
    ((i) =>
        query += `(${i.id}, '${i.name}', '${i.img}'),
        `);

// с сайта https://www.mobafire.com/edit/guide получить список итемов (выполнить в консоли разработчика, когда на странице есть выбор итемов)
$('#content > div > div.mf-redesign.new-guide > div.col-left > div.new-guide__build > div.new-guide__build__section.current > div > div > div.new-guide__block__content.active > div.new-guide__items > div.new-guide__items__search > div.new-guide__items__search__main > div.bottom>div').map((i, d) => ({
    id: d.attributes.getNamedItem('item-id').value,
    img: d.querySelector('img').src,
    name: d.querySelector('span').textContent,
    price: d.querySelector('p').textContent
})).toArray().forEach
((i) =>
    query += `(${i.id}, '${i.name}', '${i.img}', ${i.price} ),
        `);