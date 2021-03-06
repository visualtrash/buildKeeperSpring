package com.nikulin.buildKeeper.controllers;


public class ConsoleController {

//    private static BuildService buildService;
//    private static HeroService heroService;
//    private static ItemService itemService;
//
//    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//    // это входная точка программы, а так же метод служит для выбора пользователем раздела в котором он будет выполнять действия
//    public static void main(String[] args) throws Exception {
//        buildService = new BuildService(new BuildRepository());
//        heroService = new HeroService(new HeroRepository());
//        itemService = new ItemService(new ItemRepository());
//
//        while (true) {
//            // ввод команды юзера
//            System.out.println("What to edit? BUILD(b) / HERO(h) / ITEM(i)");
//            String userChoice = reader.readLine().toLowerCase();
//
//            switch (userChoice) {
//                case Commands.BUILD_COMMAND:
//                    editBuilds();
//                    break;
//                case Commands.HERO_COMMAND:
//                    editHeroes();
//                    break;
//                case Commands.ITEM_COMMAND:
//                    editItems();
//                    break;
//                case Commands.EXIT_COMMAND:
//                    return;
//                default:
//                    System.out.println("Unknown command :(");
//                    break;
//            }
//        }
//    }
//
//    // в этом методе мы обрабатываем все пользовательские действия над итемами
//    private static void editItems() throws Exception {
//        System.out.println("What do you want to do with ITEMs? ADD(-a) / REMOVE(-r) / SHOW all ITEMS(-g) / SHOW ITEM by id(-gid)");
//        String userItemCommand = reader.readLine().toLowerCase();
//
//        switch (userItemCommand) {public
//            case Commands.ADD_COMMAND:
//                createItem();
//                break;
//            case Commands.REMOVE_COMMAND:
//                removeItem();
//                break;
//            case Commands.GET_ALL_COMMAND:
//                showItems();
//                break;
//            case Commands.GET_BY_ID_COMMAND:
//                showItemsById();
//                break;
//            case Commands.EXIT_COMMAND:
//                // выходим из раздела
//                return;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//    }
//
//    // в этом методе мы обрабатываем все пользовательские действия над героями
//    private static void editHeroes() throws Exception {
//        System.out.println("What do you want to do with HEROES? ADD(-a) / REMOVE(-r) / SHOW all HEROES(-g) / SHOW HERO by id(-gid)");
//        String userHeroCommand = reader.readLine().toLowerCase();
//
//        switch (userHeroCommand) {
//            case Commands.ADD_COMMAND:
//                createHero();
//                break;
//            case Commands.REMOVE_COMMAND:
//                removeHero();
//                break;
//            case Commands.GET_ALL_COMMAND:
//                showHeroes();
//                break;
//            case Commands.GET_BY_ID_COMMAND:
//                showHeroesById();
//                break;
//            case Commands.EXIT_COMMAND:
//                // выходим из раздела
//                return;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//    }
//
//    // в этом методе мы обрабатываем все пользовательские действия над билдами
//    private static void editBuilds() throws Exception {
//        System.out.println("What do you want to do with BUILD? ADD(-a) / UPDATE(-u) / REMOVE(-r) / SHOW all BUILDS(-g) / SHOW BUILD by id(-gid)");
//        String userBuildCommand = reader.readLine().toLowerCase();
//
//        switch (userBuildCommand) {
//            case Commands.ADD_COMMAND:
//                createBuild();
//                break;
//            case Commands.REMOVE_COMMAND:
//                removeBuild();
//                break;
//            case Commands.GET_ALL_COMMAND:
//                showBuilds();
//                break;
//            case Commands.GET_BY_ID_COMMAND:
//                showBuildsById();
//                break;
//            case Commands.UPDATE_COMMAND:
//                updateBuild();
//                break;
//            case Commands.EXIT_COMMAND:
//                // выходим из раздела
//                return;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//
//    }
//
//    private static void updateBuild() throws Exception {
//        System.out.println("What do you want to EDIT in BUILD? HERO(h) / ITEM(i)");
//        String userUpdateBuildCommand = reader.readLine().toLowerCase();
//
//        switch (userUpdateBuildCommand) {
//            case Commands.HERO_COMMAND:
//                updateBuildHero();
//                break;
//            case Commands.ITEM_COMMAND:
//                editItemsOfBuild();
//                break;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//    }
//
//    private static void updateBuildHero() throws IOException {
//        System.out.println("What do you want to EDIT in HERO? POSITION(p) / NAME(n)");
//        String userHeroCommand = reader.readLine().toLowerCase();
//        switch (userHeroCommand) {
//            case "p":
//                System.out.println("enter the new POSITION of HERO");
//                String userHeroPosition = reader.readLine().toLowerCase();
//                findBuild().setHeroPosition(userHeroPosition);
//                break;
//            case "n":
//                System.out.println("enter the new NAME of HERO");
//                String userHeroName = reader.readLine().toLowerCase();
//                findBuild().getHero().setName(userHeroName);
//                break;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//    }
//
//    private static void removeBuild() throws Exception {
//        System.out.println("enter the ID of BUILD for REMOVE");
//        UUID userBuildRemoveId = UUID.fromString(reader.readLine());
//
//        buildService.removeById(userBuildRemoveId);
//    }
//
//    // метод спрашивает у пользователя все необходимое для добавления билда
//    // и отдает эту информацию в сервис для выполнения логики создания
//    private static void createBuild() throws Exception {
//        System.out.println("enter the NAME of BUILD");
//        String userBuildName = reader.readLine();
//        System.out.println("enter the NAME of HERO");
//        String userHeroName = reader.readLine();
//        System.out.println("enter the POSITION of HERO");
//        String userHeroPosition = reader.readLine();
//
//        List<Item> userBuildItems = new ArrayList<>();
//
//        // цикл для ввода массива итемов
//        itemsEnterCycle:
//        while (true) {
//            System.out.println("enter the NAME of ITEM");
//            String itemName = reader.readLine();
//
//            userBuildItems.add(new Item(itemName));
//
//            // цикл для спрашивания "добавить еще?"
//            String oneMoreItemName = "y";
//            oneMoreCycle:
//            while (!oneMoreItemName.equals("y") || !oneMoreItemName.equals("n")) {
//                System.out.println("do u want to add one more item? Y/N");
//                oneMoreItemName = reader.readLine().toLowerCase();
//
//                // если пользователь ответил нет, то выходим из цикла спрашивания
//                if (oneMoreItemName.equalsIgnoreCase("n")) {
//                    break itemsEnterCycle;
//                } else if (oneMoreItemName.equalsIgnoreCase("y")) {
//                    // тут лейбл просто для понимания какой цикл брейкаем
//                    break oneMoreCycle;
//                } else System.out.println("incorrect. enter Y/N");
//            }
//        }
//
//        List<Ability> abilities = new ArrayList<>();
//        // цикл для ввода массива умений
//        for (int i = 1; i < 3; i++) {
//            System.out.println("enter the ABILITY on " + i + " lvl (Q / W / E / R)");
//            String userAbility = reader.readLine().toUpperCase();
//
//            if (i < 2 && userAbility.equals(Ability.R)) {
//                System.out.println("error. R ability is available after 6 lvl :(");
//                break;
//            } else {
//                switch (userAbility) {
//                    case "Q":
//                        abilities.add(Ability.Q);
//                        break;
//                    case "W":
//                        abilities.add(Ability.W);
//                        break;
//                    case "E":
//                        abilities.add(Ability.E);
//                        break;
//                    case "R":
//                        abilities.add(Ability.R);
//                        break;
//                    default:
//                        System.out.println("Unknown command :(");
//                        break;
//                }
//            }
//        }
//        buildService.add(userBuildName, new Hero(userHeroName), userBuildItems, new Rune(), abilities, userHeroPosition);
//    }
//
//    private static void showBuilds() {
//        Gson gson = new Gson();
//        String buildAsString = gson.toJson(buildService.getBuildList());
//        System.out.println(buildAsString);
//    }
//
//    private static void createItem() throws Exception {
//        System.out.println("enter the NAME of new ITEM");
//        String itemName = reader.readLine().toLowerCase();
//
//        itemService.add(itemName);
//    }
//
//    private static void showItems() {
//        Gson gson = new Gson();
//        String itemListAsString = gson.toJson(itemService.getAll());
//        System.out.println(itemListAsString);
//    }
//
//    private static void showItemsById() throws IOException {
//
//        System.out.println("enter the ID of ITEM");
//        UUID itemId = UUID.fromString(reader.readLine());
//
//        List<Item> list = itemService.getAll();
//        for (Item eachItem : list) {
//            if (eachItem.getId().equals(itemId)) {
//                Gson gson = new Gson();
//                String itemAsString = gson.toJson(eachItem);
//                System.out.println(itemAsString);
//            }
//        }
//    }
//
//    private static void removeItem() throws Exception {
//        System.out.println("enter the ID of ITEM for REMOVE");
//        UUID userItemRemoveId = UUID.fromString(reader.readLine());
//
//        itemService.removeById(userItemRemoveId);
//    }
//
//    private static void createHero() throws Exception {
//        System.out.println("enter the NAME of new HERO");
//        String heroName = reader.readLine().toLowerCase();
//
//        heroService.add(heroName);
//    }
//
//    private static void removeHero() throws Exception {
//        System.out.println("enter the ID of HERO for REMOVE");
//        UUID userHeroRemoveId = UUID.fromString(reader.readLine());
//
//        heroService.removeById(userHeroRemoveId);
//    }
//
//    private static void showHeroes() {
//        Gson gson = new Gson();
//        String heroListAsString = gson.toJson(heroService.getHeroList());
//        System.out.println(heroListAsString);
//    }
//
//    private static void showHeroesById() throws IOException {
//        System.out.println("enter the ID of HERO");
//        UUID heroId = UUID.fromString(reader.readLine());
//
//        List<Hero> list = heroService.getHeroList();
//        for (Hero eachHero : list) {
//            if (eachHero.getId().equals(heroId)) {
//                Gson gson = new Gson();
//                String heroAsString = gson.toJson(eachHero);
//                System.out.println(heroAsString);
//            }
//        }
//    }
//
//    private static void showBuildsById() throws IOException {
//        System.out.println("enter the ID of BUILD");
//        UUID buildId = UUID.fromString(reader.readLine());
//
//        List<Build> list = buildService.getBuildList();
//        for (Build eachBuild : list) {
//            if (eachBuild.getId().equals(buildId)) {
//                Gson gson = new Gson();
//                String buildAsString = gson.toJson(eachBuild);
//                System.out.println(buildAsString);
//            }
//        }
//    }
//
//    private static Build findBuild() throws IOException {
//        System.out.println("enter the ID of BUILD");
//        UUID buildId = UUID.fromString(reader.readLine());
//        Build build = null;
//
//        List<Build> list = buildService.getBuildList();
//        for (Build eachBuild : list) {
//            if (eachBuild.getId().equals(buildId)) {
//                build = eachBuild;
//            } else System.out.println("cannot find build :(");
//        }
//        return build;
//    }
//
//    private static Item getItemFromList() throws IOException {
//        System.out.println("enter the ID of ITEM");
//        UUID itemId = UUID.fromString(reader.readLine());
//        Item item = null;
//
//        List<Item> list = findBuild().getItems();
//        for (Item eachItem : list) {
//            if (eachItem.getId().equals(itemId)) {
//                item = eachItem;
//            } else System.out.println("cannot find item :(");
//        }
//        return item;
//    }
//
//    private static void editItemsOfBuild() throws Exception {
//        System.out.println("What do you want to do with ITEM? ADD(-a) / REMOVE(-r)");
//        String userItemCommand = reader.readLine().toLowerCase();
//
//        switch (userItemCommand) {
//            case Commands.ADD_COMMAND:
//                addItem();
//                break;
//            case Commands.REMOVE_COMMAND:
//                deleteItem();
//                break;
//            default:
//                System.out.println("Unknown command :(");
//                break;
//        }
//    }
//
//    private static void deleteItem() throws Exception {
//        findBuild().getItems().remove(getItemFromList());
//    }
//
//    private static void addItem() throws Exception {
//        System.out.println("enter the NAME of new ITEM");
//        String userItemName = reader.readLine().toLowerCase();
//
//        List<Item> list = findBuild().getItems();
//        list.add(new Item(userItemName));
//    }


}

