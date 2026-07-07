# Changelog

### Added 

(07/07/2026)
- Added unit tests for:

  - GeoLocationService
  - MapLinkService
  - PlayerService
  - UserPlayerService
  - UserService
- Added additional custom exceptions for clearer service-layer error handling.

### Changed

(07/07/2026)
- Refactored service methods to improve readability and reduce duplicated logic.
- Added reusable entity lookup methods for common repository queries.
- Improved update methods to detect changed values before performing unnecessary operations.

### Improved

(07/07/2026)
- Increased service-layer test coverage.
- Improved maintainability by separating validation, lookup, and update responsibilities.
