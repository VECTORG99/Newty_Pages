# Newty_Pages

Skeleton for building data-driven pages with the Newty pattern inside a Quarkus + Qute application.

## Pattern

```
ScraperService  →  fetches + parses remote data
PageResource    →  REST endpoint, passes data to template  
page.html       →  Qute template with period toggle + show more/less
messages_*.properties → i18n keys (EN canonical, ES mirror)
```

## Quick start

1. Copy `src/service/ScraperService.java` → adapt:
   - Replace `Item` record with your domain model
   - Replace `buildUrl()` with your data source
   - Replace `parse()` with your HTML/JSON parser
   - Replace `translate()` with your text transformation

2. Copy `src/resource/PageResource.java` → adapt:
   - Replace `COUNT_STEPS` with your toggle sequence
   - Uncomment `@CheckedTemplate` and `TemplateInstance` return
   - Inject your service

3. Copy `src/template/page.html` → adapt:
   - Replace `mypage_*` prefix with your i18n key prefix
   - Match your project's CSS classes (btn-primary, layout/main)
   - Replace `item.fieldName` with your record fields

4. Copy `src/i18n/messages.properties` → adapt:
   - Replace `mypage_*` keys with yours
   - Add `_es.properties` mirror with Spanish translations
   - Add `@Message` methods to your MessageBundle interface

## Rules
- Zero hardcoded user-visible strings (all via `{i18n:key}`)
- Server computes toggle state (nextCount, isMax) — template just renders
- Scraper returns empty list on failure (never null, never throws)
- Sort data server-side before passing to template

## Scheduling (optional)

Add `@Scheduled(cron="...")` to your service for periodic cache refresh:

```java
@Scheduled(cron = "0 0 0 * * ?")   // midnight daily
void refreshDaily() { cache.put("daily", fetch(...)); }
```
