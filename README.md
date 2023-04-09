# Serwis bankomatów

## Spis treści
* [Opis](#opis)
* [Zadanie](#zadanie)
* [Uruchomienie aplikacji](#uruchomienie-aplikacji)
* [Przykład zapytania](#przykład-zapytania)
* [Przykład odpowiedzi](#przykład-odpowiedzi)
* [Technologie](#technologie)

## Opis

Zespół serwisujący bankomaty przychodzi do pracy po długim weekendzie. 
System zleceń serwisowych zawiera zgłoszenia, które muszą ustawić w kolejce obsługi. 
Kolejka związana jest trasą przejazdu grupy konwojowej przez poszczególne regiony a wyznaczona trasa przejazdu pomiędzy regionami została już zatwierdzona.
Zleceń jest bardzo dużo ponieważ oprócz zaplanowanych na dzisiaj standardowych i priorytetowych prac zasilenia bankomatu, pojawiły się inne zgłoszenia które również trzeba obsłużyć. 
Jednym z takich zgłoszeń jest sygnał o niskim stanie gotówki bankomatu, który nie był na dzisiaj zaplanowany takie zgłoszenie powinno być zrealizowane zaraz po zakończeniu prac nad zleceniami planowanymi priorytetowymi w danym regionie. 
Innym jest sygnał o awarii bankomatu z którym zespół techniczny nie ma komunikacji i nie może przeprowadzić procedury zdalnego ponownego uruchomienia. Takie zgłoszenie powinno zostać zrealizowane w pierwszej kolejności w danym regionie. 
Priorytetowe planowane zasilenie bankomatu dotyczy urządzenia gdzie trend zużycia stanu gotówki jest wysoki dlatego ta operacja jest wykonywana przez zleceniami standardowymi. 

## Zadanie
Przygotuj system który przygotuje odpowiednią kolejność zleceń do obsługi dla grupy konwojowej, przy założeniu że:
1)	konwój przejeżdża przez regiony biorąc pod uwagę rosnącą kolejność przydzielonych im numerów,
2)	dany bankomat na liście zleceń dla grupy konwojowej może wystąpić tylko raz.

## Uruchomienie aplikacji
```
git clone https://github.com/dominikjaniga91/atm-service.git
bash build.sh
bash run.sh
```

## Przykład zapytania

```
[
  {
    "region": 4,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 1,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 2,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 3,
    "requestType": "PRIORITY",
    "atmId": 2
  },
  {
    "region": 3,
    "requestType": "STANDARD",
    "atmId": 1
  },
  {
    "region": 2,
    "requestType": "SIGNAL_LOW",
    "atmId": 1
  },
  {
    "region": 5,
    "requestType": "STANDARD",
    "atmId": 2
  },
  {
    "region": 5,
    "requestType": "FAILURE_RESTART",
    "atmId": 1
  }
]
```

## Przykład odpowiedzi

```
[
  {
    "region": 1,
    "atmId": 1
  },
  {
    "region": 2,
    "atmId": 1
  },
  {
    "region": 3,
    "atmId": 2
  },
  {
    "region": 3,
    "atmId": 1
  },
  {
    "region": 4,
    "atmId": 1
  },
  {
    "region": 5,
    "atmId": 1
  },
  {
    "region": 5,
    "atmId": 2
  }
]
```
## Technologie

Java 17

Spring Boot 3.0.5

Maven 3.9.0
