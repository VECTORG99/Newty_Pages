# Newty_Pages

Esqueleto para construir páginas web data-driven usando el patrón Newty dentro de una aplicación Quarkus + Qute.

Parte del ecosistema [Newty](https://github.com/VECTORG99/newty) — el modelo conceptual 3-capas (Extractor → Transformador → Renderizador) aplicado al renderizado de páginas.

## Alcance

Este repo contiene el **esqueleto mínimo** para integrar una página Newty en cualquier proyecto Quarkus:

| Archivo | Rol |
|---|---|
| `src/service/ScraperService.java` | Fetch + parse de fuente externa (GitHub Trending, API, RSS) |
| `src/resource/PageResource.java` | Endpoint REST con query params (period, count), UI state server-side |
| `src/template/page.html` | Template Qute con period toggle + show more/less, cero strings hardcodeados |
| `src/i18n/messages.properties` | i18n base (EN canónico, copiar a `_es.properties` para ES mirror) |

## Ecosistema Newty

| Repo | Qué hace |
|---|---|
| [Newty](https://github.com/VECTORG99/newty) | Script CLI standalone (Python) + modelo conceptual (ABSTRACTION.md) |
| **Newty_Pages** (este repo) | Esqueleto para páginas Quarkus + Qute + i18n |
| Newty_Bot (futuro) | Mismo extractor → JSON → webhook Discord/Slack |

## Quick start

1. Copiar `ScraperService.java` → reemplazar `Item` record, `buildUrl()`, `parse()`, `translate()`
2. Copiar `PageResource.java` → ajustar `COUNT_STEPS`, descomentar `@CheckedTemplate`, inyectar tu servicio
3. Copiar `page.html` → reemplazar `mypage_*` por tus claves i18n, ajustar CSS a tu proyecto
4. Copiar `messages.properties` → renombrar claves, crear `messages_es.properties` mirror

## Reglas de diseño

1. **Cero strings hardcodeados** — todo texto visible va por `{i18n:clave}`
2. **Extractor nunca rompe** — retorna lista vacía si falla (nunca null, nunca excepción)
3. **UI state server-side** — `nextCount`, `isMax` los calcula el resource, el template solo renderiza
4. **Ordenamiento server-side** — los datos llegan ordenados al template
5. **Caché con `@Scheduled`** — opcional: refresh periódico (medianoche diario/semanal/mensual)

## Licencia

MIT
